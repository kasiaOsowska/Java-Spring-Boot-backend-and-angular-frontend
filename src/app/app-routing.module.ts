import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientsListComponent } from './patients/view/patients-list/patients-list/patients-list.component';
import { PatientsViewComponent } from './patients/view/patients-view/patients-view.component';
import { PatientsEditComponent } from './patients/view/patients-edit/patients-edit.component';
import { PatientsAddComponent } from './patients/view/patients-add/patients-add.component';

const routes: Routes = [
  {
    component: PatientsListComponent,
    path: ""
  },
  {
    component: PatientsViewComponent,
    path: "patients/:uuid"
  },
  {
    component: PatientsEditComponent,
    path: "patients/:uuid/edit"
  },
  {
    component: PatientsAddComponent,
    path: "patients/add/new"
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
