import {Poem} from "../../poem/poem.model";

export class Artist {
  id?: number;
  firstName: string;
  lastName: string;
  birthDate: Date;
  deathDate: Date;
  biography: string;
  poems: Poem[];
  //visitors: Visitor[];

  constructor(firstName: string, lastName: string, birthDate: Date, deathDate: Date, biography: string, poems: Poem[]) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.deathDate = deathDate;
    this.biography = biography;
    this.poems = poems;
    //this.visitors = visitors;
  }
}
