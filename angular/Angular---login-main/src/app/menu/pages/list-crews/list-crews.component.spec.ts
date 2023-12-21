import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCrewsComponent } from './list-crews.component';

describe('ListCrewsComponent', () => {
  let component: ListCrewsComponent;
  let fixture: ComponentFixture<ListCrewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCrewsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListCrewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
