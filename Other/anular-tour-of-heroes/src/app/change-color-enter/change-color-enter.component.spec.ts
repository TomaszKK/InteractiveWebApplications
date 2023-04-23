import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeColorEnterComponent } from './change-color-enter.component';

describe('ChangeColorEnterComponent', () => {
  let component: ChangeColorEnterComponent;
  let fixture: ComponentFixture<ChangeColorEnterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeColorEnterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangeColorEnterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
