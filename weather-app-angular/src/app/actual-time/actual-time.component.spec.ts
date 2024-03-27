import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActualTimeComponent } from './actual-time.component';

describe('ActualTimeComponent', () => {
  let component: ActualTimeComponent;
  let fixture: ComponentFixture<ActualTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ActualTimeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ActualTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
