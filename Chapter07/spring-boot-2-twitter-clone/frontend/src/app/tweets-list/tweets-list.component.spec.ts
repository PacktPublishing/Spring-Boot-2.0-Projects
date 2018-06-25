import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TweetsListComponent } from './tweets-list.component';

describe('TweetsListComponent', () => {
  let component: TweetsListComponent;
  let fixture: ComponentFixture<TweetsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TweetsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TweetsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
