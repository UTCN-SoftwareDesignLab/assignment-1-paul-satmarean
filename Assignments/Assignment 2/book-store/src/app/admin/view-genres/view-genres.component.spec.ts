import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewGenresComponent } from './view-genres.component';

describe('ViewGenresComponent', () => {
  let component: ViewGenresComponent;
  let fixture: ComponentFixture<ViewGenresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewGenresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewGenresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
