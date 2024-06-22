import {Component, Input} from '@angular/core';
import {CardModule} from "primeng/card";
import {ImageModule} from "primeng/image";
import {PrimeTemplate} from "primeng/api";
import {RouterLink} from "@angular/router";
import {DatePipe} from "@angular/common";
import {IPost} from "../../models/product.model";

@Component({
  selector: 'app-card-post',
  standalone: true,
  imports: [
    CardModule,
    ImageModule,
    PrimeTemplate,
    RouterLink,
    DatePipe
  ],
  templateUrl: './card-post.component.html',
  styleUrl: './card-post.component.scss'
})
export class CardPostComponent {
  @Input() value?: IPost;
  @Input() heightImage?: string;
}
