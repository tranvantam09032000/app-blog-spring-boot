import {Directive, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ImageContentComponent} from "../components/image-content/image-content.component";
import {SliderContentComponent} from "../components/slider-content/slider-content.component";
import {TextContentComponent} from "../components/text-content/text-content.component";

@Directive({
  selector: '[componentGenerationDirective]',
  standalone: true
})
export class ComponentGenerationDirective implements OnInit {
  @Input() type = "";
  @Input() value?: any

  constructor(private viewContainerRef: ViewContainerRef) {

  }

  ngOnInit() {
    switch (this.type) {
      case "image": {
        const componentRef = this.viewContainerRef.createComponent(ImageContentComponent);
        componentRef.setInput("value", this.value);
        break;
      }
      case "slider": {
        const componentRef = this.viewContainerRef.createComponent(SliderContentComponent);
        componentRef.setInput("value", this.value);
        break;
      }
      default: {
        const componentRef = this.viewContainerRef.createComponent(TextContentComponent);
        componentRef.setInput("value", this.value);

      }
    }
  }
}
