import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlurActionComponent } from './blur-action.component';

describe('BlurActionComponent', () => {
  let component: BlurActionComponent;
  let fixture: ComponentFixture<BlurActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlurActionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BlurActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
