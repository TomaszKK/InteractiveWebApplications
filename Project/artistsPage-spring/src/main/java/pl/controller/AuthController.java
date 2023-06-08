package pl.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.message.request.LoginForm;
import pl.message.request.SignUpForm;
import pl.message.response.JwtResponse;
import pl.message.response.ResponseMessage;
import pl.model.*;
import pl.repository.*;
import pl.security.jwt.JwtProvider;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {

    private DaoAuthenticationProvider daoAuthenticationProvider;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;
    private ArtistRepository artistRepository;
    private VisitorRepository visitorRepository;
    private AdminRepository adminRepository;

    @Autowired
    public AuthController(DaoAuthenticationProvider daoAuthenticationProvider, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, ArtistRepository artistRepository, VisitorRepository visitorRepository, AdminRepository adminRepository) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.artistRepository = artistRepository;
        this.visitorRepository = visitorRepository;
        this.adminRepository = adminRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = daoAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("moj:"+jwt);
        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken."), HttpStatus.BAD_REQUEST);
        }

        // Create user account
        User user = new User(signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: Admin Role not found."));
                    roles.add(adminRole);
                    User adminUser = new User(user.getUsername(), user.getPassword());
                    adminUser.setRoles(roles);
                    userRepository.save(adminUser);
                    Admin admin = new Admin();
                    admin.setUser(adminUser);
                    break;
                case "artist":
                    Role artistRole = roleRepository.findByName(RoleName.ROLE_ARTIST)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: Artist Role not found."));
                    roles.add(artistRole);
                    User artistUser = new User(user.getUsername(), user.getPassword());
                    artistUser.setRoles(roles);
                    userRepository.save(artistUser);
                    Artist artist = new Artist();
                    artist.setUser(artistUser);
                    artistRepository.save(artist);
                    break;
                default:
                    Role visitorRole = roleRepository.findByName(RoleName.ROLE_VISITOR)
                            .orElseThrow(() -> new RuntimeException("Fail -> Cause: Visitor Role not found."));
                    roles.add(visitorRole);
                    User visitorUser = new User(user.getUsername(), user.getPassword());
                    visitorUser.setRoles(roles);
                    userRepository.save(visitorUser);
                    Visitor visitor = new Visitor();
                    visitor.setUser(visitorUser);
                    visitorRepository.save(visitor);
                    break;
            }
        });

        return new ResponseEntity<>(new ResponseMessage("User registered successfully."), HttpStatus.OK);
    }

}
