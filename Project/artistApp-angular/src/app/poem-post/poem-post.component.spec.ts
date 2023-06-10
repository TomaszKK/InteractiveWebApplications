import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoemPostComponent } from './poem-post.component';

describe('PoemPostComponent', () => {
  let component: PoemPostComponent;
  let fixture: ComponentFixture<PoemPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoemPostComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PoemPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
