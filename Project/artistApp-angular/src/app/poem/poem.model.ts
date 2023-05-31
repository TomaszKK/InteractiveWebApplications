import {ArtistModel} from "../artist/artist.model";
import {VisitorModel} from "../visitor/visitor.model";

export class PoemModel {
  id?: number;
  title: string;
  text: string;
  genre: string;
  creationDate: Date;
  rating: number;
  numberOfRatings: number;
  artist: ArtistModel;
  visitors: VisitorModel[];

  constructor(title: string, text: string, genre: string, creationDate: Date, rating: number, numberOfRatings: number, artist: ArtistModel, visitors: VisitorModel[]) {
    this.title = title;
    this.text = text;
    this.genre = genre;
    this.creationDate = creationDate;
    this.rating = rating;
    this.numberOfRatings = numberOfRatings;
    this.artist = artist;
    this.visitors = visitors;
  }
}
