import {Artist} from "../artist/artist.model";


export class Poem {
  id?: number;
  title: string;
  text: string;
  genre: string;
  creationDate: Date;
  rating: number;
  numberOfRatings: number;
  artist: Artist;
  //visitors: Visitor[];

  constructor(title: string, text: string, genre: string, creationDate: Date, rating: number, numberOfRatings: number, artist: Artist) {
    this.title = title;
    this.text = text;
    this.genre = genre;
    this.creationDate = creationDate;
    this.rating = rating;
    this.numberOfRatings = numberOfRatings;
    this.artist = artist;
    //this.visitors = visitors;
  }

}
