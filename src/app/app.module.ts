import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { PatientsViewComponent } from './patients/view/patients-view/patients-view.component';
import { PatientsService } from './patients/service/patients.service';
import { PatientsListComponent } from './patients/view/patients-list/patients-list/patients-list.component';
import { PatientsEditComponent } from './patients/view/patients-edit/patients-edit.component';
import { DoctorsService } from './doctor/service/doctors.service';
import { FormsModule } from '@angular/forms';
import { PatientsAddComponent } from './patients/view/patients-add/patients-add.component';

@NgModule({
  declarations: [
    AppComponent,
    PatientsViewComponent,
    PatientsListComponent,
    PatientsEditComponent,
    PatientsAddComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PatientsService,
  DoctorsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
