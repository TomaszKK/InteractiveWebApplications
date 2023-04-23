import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoubleClickComponent } from './double-click.component';

describe('DoubleClickComponent', () => {
  let component: DoubleClickComponent;
  let fixture: ComponentFixture<DoubleClickComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoubleClickComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoubleClickComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
