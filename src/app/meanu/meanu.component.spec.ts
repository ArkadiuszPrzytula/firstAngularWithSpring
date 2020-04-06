import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MeanuComponent } from './meanu.component';

describe('MeanuComponent', () => {
  let component: MeanuComponent;
  let fixture: ComponentFixture<MeanuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MeanuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MeanuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
