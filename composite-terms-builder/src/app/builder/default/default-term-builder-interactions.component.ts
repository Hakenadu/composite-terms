import {Component} from '@angular/core';

export interface FormTermBuilderInteraction {
  name: string;
}

@Component({
  selector: 'term-builder-interactions',
  templateUrl: './default-term-builder-interactions.component.html'
})
export class DefaultTermBuilderInteractionsComponent {

  interactions: FormTermBuilderInteraction[] = [
    {
      name: 'Add Operation'
    },
    {
      name: 'Add Constant'
    },
    {
      name: 'Add Variable'
    },
    {
      name: 'Remove Term'
    }
  ];
}
