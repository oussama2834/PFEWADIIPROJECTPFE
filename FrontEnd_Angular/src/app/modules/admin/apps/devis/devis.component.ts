import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AsyncPipe, CurrencyPipe, DatePipe, NgClass, NgFor, NgIf, NgTemplateOutlet } from '@angular/common';
import { FormsModule, ReactiveFormsModule, UntypedFormControl } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatOptionModule, MatRippleModule } from '@angular/material/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { fuseAnimations } from '@fuse/animations';
import { TableModule } from 'primeng/table';
import { DevisServiceService } from './devis-service.service';
import { DevisDialogComponent } from '../devis-dialog/devis-dialog.component';

@Component({
  selector: 'app-devis',
  templateUrl: './devis.component.html',
    styleUrls: ['./devis.component.scss'],
    encapsulation  : ViewEncapsulation.None,
    changeDetection: ChangeDetectionStrategy.OnPush,
    animations     : fuseAnimations,
    standalone     : true,
    imports        : [NgIf,DatePipe,TableModule,MatCardModule,MatTableModule,MatProgressBarModule,MatDialogModule,MatRadioModule, MatFormFieldModule, MatIconModule, MatInputModule, FormsModule, ReactiveFormsModule, MatButtonModule, MatSortModule, NgFor, NgTemplateOutlet, MatPaginatorModule, NgClass, MatSlideToggleModule, MatSelectModule, MatOptionModule, MatCheckboxModule, MatRippleModule, AsyncPipe, CurrencyPipe],
})
export class DevisComponent implements OnInit {
    devis: any;
    searchInputControl: UntypedFormControl = new UntypedFormControl();

    constructor(
        private devisService: DevisServiceService,
        private _dialog: MatDialog,
        private cdRef: ChangeDetectorRef
    )
    {}
    getCommands() {
        this.devisService.listDevis().subscribe((res) => {
            this.devis = res
            console.log(this.devis)
            this.cdRef.detectChanges();

        }, (error) => {
            console.log(error);
     })
    }
    openAddDialog() {
        console.log("opendialog")
        const dialogRef = this._dialog.open(DevisDialogComponent, { data:{isAdding: true} });
       dialogRef.afterClosed().subscribe({
         next: (val) => {
           if (val) {

           }
         },
       });
    }
    openEditDialog(devis:any) {
        console.log("opendialog")
        const dialogRef = this._dialog.open(DevisDialogComponent, { data:{isEditing: true,devis} });
       dialogRef.afterClosed().subscribe({
         next: (val) => {
           if (val) {

           }
         },
       });
    }
    ngOnInit() {
        this.getCommands();
    }
    deleteCommande(id:number) {
        this.devisService.deleteDevis(id).subscribe((res) => {
            console.log(res)
        }, (error) => {
            console.log(error)
        })
        this.devis = this.devis.filter(d => d.idDevis != id)
    }

}
