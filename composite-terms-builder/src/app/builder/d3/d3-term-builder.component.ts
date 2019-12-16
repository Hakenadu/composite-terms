import {Component, ElementRef, ViewChild} from '@angular/core';

@Component({
  selector: 'term-builder-d3',
  templateUrl: './d3-term-builder.component.html'
})
export class D3TermBuilderComponent {

  @ViewChild('d3-canvas', {static: false})
  d3Canvas: ElementRef;
}
