import {Component, Input} from '@angular/core';
import {GalleriaModule} from "primeng/galleria";

@Component({
  selector: 'app-slider-content',
  standalone: true,
  imports: [
    GalleriaModule
  ],
  templateUrl: './slider-content.component.html',
  styleUrl: './slider-content.component.scss'
})
export class SliderContentComponent {
  @Input() value: string[] = [];
}
