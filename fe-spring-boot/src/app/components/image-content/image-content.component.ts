import {Component, Input} from '@angular/core';
import {ImageModule} from "primeng/image";

@Component({
  selector: 'app-image-content',
  standalone: true,
  imports: [
    ImageModule
  ],
  templateUrl: './image-content.component.html',
  styleUrl: './image-content.component.scss'
})
export class ImageContentComponent {
  @Input() value: string = "";
}
