import {PoemModel} from "../poem/poem.model";

export class VisitorModel {
  id?: number;
  name?: string;
  surname?: string;
  likedPoems?: PoemModel[];

  constructor(name: string, surname: string, likedPoems: PoemModel[]) {
    this.name = name;
    this.surname = surname;
    this.likedPoems = likedPoems;
  }
}
