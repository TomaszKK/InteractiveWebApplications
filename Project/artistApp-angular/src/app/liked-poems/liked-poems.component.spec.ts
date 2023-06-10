import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LikedPoemsComponent } from './liked-poems.component';

describe('LikedPoemsComponent', () => {
  let component: LikedPoemsComponent;
  let fixture: ComponentFixture<LikedPoemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LikedPoemsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LikedPoemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
