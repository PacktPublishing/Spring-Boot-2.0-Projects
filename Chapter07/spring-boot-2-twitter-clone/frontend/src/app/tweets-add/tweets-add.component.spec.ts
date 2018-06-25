import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TweetsAddComponent } from './tweets-add.component';

describe('TweetsAddComponent', () => {
  let component: TweetsAddComponent;
  let fixture: ComponentFixture<TweetsAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TweetsAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TweetsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
