export class SignupInfo {
  username: string;
  role: string[];
  password: string;

  constructor(username: string, password: string) {
    this.username = username;
    this.role = ['artist'];
    this.password = password;
  }
}
