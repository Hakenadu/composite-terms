import {Component} from '@angular/core';
import {Term} from './builder/shared';

@Component({
  selector: 'term-demo',
  template: `
    <term-builder [term]="myTerm"></term-builder>
  `
})
export class DemoComponent {

  myTerm: Term = {
    type: 'operation',
    operator: 'subtract',
    operands: [
      {
        type: 'variable',
        name: 'test'
      },
      {
        type: 'operation',
        operator: 'add',
        operands: [
          {
            type: 'constant',
            datatype: 'string',
            value: '500'
          },
          {
            type: 'variable',
            name: 'x'
          }
        ]
      },
    ]
  };
}
