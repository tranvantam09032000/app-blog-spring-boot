import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-text-content',
  standalone: true,
  imports: [],
  templateUrl: './text-content.component.html',
  styleUrl: './text-content.component.scss'
})
export class TextContentComponent {
  @Input() value: string = "";
}
