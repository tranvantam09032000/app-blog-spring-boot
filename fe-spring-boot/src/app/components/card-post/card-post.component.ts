import {Component, Input} from '@angular/core';
import {CardModule} from "primeng/card";
import {ImageModule} from "primeng/image";
import {PrimeTemplate} from "primeng/api";
import {IPost} from "../../models.model";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-card-post',
  standalone: true,
  imports: [
    CardModule,
    ImageModule,
    PrimeTemplate,
    RouterLink
  ],
  templateUrl: './card-post.component.html',
  styleUrl: './card-post.component.scss'
})
export class CardPostComponent {
  @Input() value?: IPost;
  @Input() heightImage?: string;
}
