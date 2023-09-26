import { NgIf, NgFor, NgTemplateOutlet, NgClass, AsyncPipe, CurrencyPipe } from '@angular/common';
import { AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule, UntypedFormControl } from '@angular/forms';
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
import { CommandeServiceService } from './commande-service.service';
import { CommandeDialogComponent } from '../commande-dialog/commande-dialog.component';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-commande',
  templateUrl: './commande.component.html',
    styleUrls: ['./commande.component.scss'],
    encapsulation  : ViewEncapsulation.None,
    changeDetection: ChangeDetectionStrategy.OnPush,
    animations     : fuseAnimations,
    standalone     : true,
    imports        : [NgIf,DatePipe,TableModule,MatCardModule,MatTableModule, MatProgressBarModule,MatDialogModule,MatRadioModule, MatFormFieldModule, MatIconModule, MatInputModule, FormsModule, ReactiveFormsModule, MatButtonModule, MatSortModule, NgFor, NgTemplateOutlet, MatPaginatorModule, NgClass, MatSlideToggleModule, MatSelectModule, MatOptionModule, MatCheckboxModule, MatRippleModule, AsyncPipe, CurrencyPipe],
})
export class CommandeComponent implements OnInit {
    commands: any
    searchInputControl: UntypedFormControl = new UntypedFormControl();
    constructor(
        private commandeService: CommandeServiceService,
        private _dialog: MatDialog,
        private cdRef: ChangeDetectorRef) {

    }
    getCommands() {
        this.commandeService.listCommands().subscribe((res) => {
            this.commands = res
            console.log(this.commands)
            this.cdRef.detectChanges();

        }, (error) => {
            console.log(error);
     })
    }
    openAddDialog() {
        console.log("opendialog")
        const dialogRef = this._dialog.open(CommandeDialogComponent, { data:{isAdding: true} });
       dialogRef.afterClosed().subscribe({
         next: (val) => {
           if (val) {

           }
         },
       });
    }
    openEditDialog(commande:any) {
        console.log("opendialog")
        const dialogRef = this._dialog.open(CommandeDialogComponent, { data:{isEditing: true,commande} });
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
        this.commandeService.deleteCommand(id).subscribe((res) => {
            console.log(res)
        }, (error) => {
            console.log(error)
        })
        this.commands = this.commands.filter(command => command.idCmd != id)
    }
}
