import { Component, OnInit } from '@angular/core';
import {Student} from "./student.model";
import {StudentService} from "./student.service";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {
  studentList?: Student[];
  student?: Student;

  constructor(private studentService: StudentService) {
  }

  ngOnInit() {
    this.getStudents();
  }

  getStudents(): void {
    this.studentService.getStudents()
      .subscribe(studentList => this.studentList = studentList);
  }

  add(firstname: string, lastname: string, email: string, telephone: string): void {
    firstname = firstname.trim();
    lastname = lastname.trim();
    email = email.trim();
    telephone = telephone.trim();
    this.studentService.addStudent({firstname, lastname, email, telephone} as Student)
      .subscribe({
        next: (student: Student) => {
          this.studentList?.push(student);
        },
        error: () => {
        },
        complete: () => {
          if (this.studentList != undefined) {
            this.studentService.totalItems.next(this.studentList.length);
            console.log(this.studentList.length);
          }
        }
      });
  }

  delete(student: Student): void {
    this.studentList = this.studentList?.filter(c => c !== student);
    this.studentService.deleteStudent(student).subscribe(() => {
        // for automatic update of number of students in parent component
        if (this.studentList != undefined) {
          this.studentService.totalItems.next(this.studentList.length);
          console.log(this.studentList.length);
        }
      }
    );
  }

  deleteAll(): void {
    this.studentService.deleteStudents().subscribe(() => {
        if (this.studentList != undefined) {
          this.studentList.length = 0;
        }
      }
    );
  }

  update(firstname: string, lastname: string, email: string, telephone: string, chosenToUpdateStudent: Student): void {
    let id = chosenToUpdateStudent.id;
    firstname = firstname.trim();
    lastname = lastname.trim();
    email = email.trim();
    telephone = telephone.trim();
    console.log(id);
    if (id != undefined) {
      this.studentService.updateStudent({firstname, lastname, email, telephone} as Student, id)
        .subscribe({
          next: (student: Student) => {
            if (this.studentList != undefined) {
              let index = this.studentList?.indexOf(chosenToUpdateStudent);
              this.studentList[index] = student;
            }
          },
          error: () => {
          },
          complete: () => {
            if (this.studentList != undefined) {
              this.studentService.totalItems.next(this.studentList.length);
              console.log(this.studentList.length);
            }
          }
        })
    }
  }

  patch(firstname: string, lastname: string, email: string, telephone: string, chosenToPatchStudent: Student): void {
    let id = chosenToPatchStudent.id;
    if (firstname == "") firstname = chosenToPatchStudent.firstname;
    if (lastname == "") lastname = chosenToPatchStudent.lastname;
    if (email == "") email = chosenToPatchStudent.email;
    if (telephone == "") telephone = chosenToPatchStudent.telephone;
    firstname = firstname.trim();
    lastname = lastname.trim();
    email = email.trim();
    telephone = telephone.trim();
    console.log(id);
    if (id != undefined) {
      this.studentService.patchStudent({firstname, lastname, email, telephone} as Student, id)
        .subscribe({
          next: (student: Student) => {
            if (this.studentList != undefined) {
              let index = this.studentList?.indexOf(chosenToPatchStudent);
              this.studentList[index] = student;
            }
          },
          error: () => {
          },
          complete: () => {
            if (this.studentList != undefined) {
              this.studentService.totalItems.next(this.studentList.length);
              console.log(this.studentList.length);
            }
          }
        })
    }
  }

  putAll(firstname: string, lastname: string, email: string, telephone: string):void {

    firstname = firstname.trim();
    lastname = lastname.trim();
    email = email.trim();
    telephone = telephone.trim();
    // @ts-ignore
    for (let i = 0; i < this.studentList.length; i++) {
      // @ts-ignore
      let id = this.studentList[i].id;
      if (id != undefined) {
        this.studentService.updateStudent({firstname, lastname, email, telephone} as Student, id)
          .subscribe({
            next: (student: Student) => {
              if (this.studentList != undefined) {
                let index = this.studentList?.indexOf(this.studentList[i]);
                this.studentList[index] = student;
              }
            },
            error: () => {
            },
            complete: () => {
              if (this.studentList != undefined) {
                this.studentService.totalItems.next(this.studentList.length);
                console.log(this.studentList.length);
              }
            }
          })
      }
    }
  }
}

