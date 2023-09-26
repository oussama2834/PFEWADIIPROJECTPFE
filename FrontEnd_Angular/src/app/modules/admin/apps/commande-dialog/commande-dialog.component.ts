import { NgIf, NgFor, NgTemplateOutlet, NgClass } from '@angular/common';
import { ChangeDetectionStrategy, Component, Inject, OnInit, ViewEncapsulation } from '@angular/core';
import { FormsModule, ReactiveFormsModule, UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatOptionModule, MatRippleModule } from '@angular/material/core';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSortModule } from '@angular/material/sort';
import { fuseAnimations } from '@fuse/animations';
import { CommandeServiceService } from '../commande/commande-service.service';

@Component({
  selector: 'app-commande-dialog',
  templateUrl: './commande-dialog.component.html',
    styleUrls: ['./commande-dialog.component.scss'],
    encapsulation  : ViewEncapsulation.None,
    changeDetection: ChangeDetectionStrategy.OnPush,
    animations     : fuseAnimations,
    standalone     : true,
    imports        : [NgIf,MatCardModule, MatProgressBarModule,MatDialogModule,MatRadioModule, MatFormFieldModule, MatIconModule, MatInputModule, FormsModule, ReactiveFormsModule, MatButtonModule, MatSortModule, NgFor, NgTemplateOutlet, MatPaginatorModule, NgClass, MatSlideToggleModule, MatSelectModule, MatOptionModule, MatCheckboxModule, MatRippleModule],
})
export class CommandeDialogComponent implements OnInit{
    AddForm: UntypedFormGroup;
    isEditing: boolean;
    isAdding: boolean;
    command : any
    constructor(
        private _formBuilder: UntypedFormBuilder,
        private commandeService: CommandeServiceService,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) {
        this.isAdding = data.isAdding;
        this.isEditing = data.isEditing;
        this.command = data.commande;
    }
    ngOnInit(): void {
        this.AddForm = this._formBuilder.group({
            dateCmd             : ['', Validators.required],
            delaiCmd      : ['',Validators.required],
            modePaiment            : ['',Validators.required],
            statut            : ['',Validators.required],
            montantToltal           : ['',Validators.required],
            adresseLivraison            : ['',Validators.required],

        });
        if (this.isEditing) {
            this.AddForm.patchValue({

              dateCmd: this.command.dateCmd,
              delaiCmd: this.command.delaiCmd,
              modePaiment: this.command.modePaiment,
              statut: this.command.statut,
              montantToltal: this.command.montantToltal,
              adresseLivraison: this.command.adresseLivraison,


            });
          }
    }

    onFormSubmit() {
        if (this.isAdding) {
            this.commandeService.ajouterCommande(this.AddForm.value).subscribe((res) => {

                console.log(res)

                window.location.reload();
         },(error)=> {
            console.log(error)
         } )
        } else if (this.isEditing) {
            const command: any = this.AddForm.value
            command.idCmd = this.command.idCmd

        this.commandeService.updateCommand(command).subscribe((res) => {

            console.log(res)

            window.location.reload();
     },(error)=> {
        console.log(error)
     } )
        }

    }
}
