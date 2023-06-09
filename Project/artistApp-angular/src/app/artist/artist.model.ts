import { PoemModel } from "../poem/poem.model";

export class ArtistModel {
  id?: number;
  name?: string;
  secondName?: string;
  bio?: string;
  mediaLinks?: string;
  location?: string;
  type?: string;
  age?: number;
  poems?: PoemModel[];

  constructor(name: string, secondName: string, bio: string, mediaLinks: string, location: string, type: string, age: number, poems: PoemModel[]) {
    this.name = name;
    this.secondName = secondName;
    this.bio = bio;
    this.mediaLinks = mediaLinks;
    this.location = location;
    this.type = type;
    this.age = age;
    this.poems = poems;
  }
}
