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
  artistName?: string;
  artistSecondName?: string;
  visitors: VisitorModel[];
  isPublic: boolean = false;
  visitorRating: number | undefined;

  constructor(title: string, text: string, genre: string, creationDate: Date, rating: number, numberOfRatings: number, artist: ArtistModel, visitors: VisitorModel[], artistName?: string, artistSecondName?: string, isPublic?: boolean, visitorRating?: number) {
    this.title = title;
    this.text = text;
    this.genre = genre;
    this.creationDate = creationDate;
    this.rating = rating;
    this.numberOfRatings = numberOfRatings;
    this.artist = artist;
    this.artistName = artistName;
    this.artistSecondName = artistSecondName;
    this.visitors = visitors;
    if(isPublic === undefined) {
      this.isPublic = false;
    }
    else {
      this.isPublic = isPublic;
    }
    this.visitorRating = visitorRating;
  }
}
