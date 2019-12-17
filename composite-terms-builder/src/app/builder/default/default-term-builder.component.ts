import {Component, Input, Output} from '@angular/core';
import {Term, TreeNode} from '../shared';
import {ITreeOptions} from 'angular-tree-component';
import {TermConversionService} from '../shared/term-conversion.service';


@Component({
  selector: 'term-builder',
  templateUrl: './default-term-builder.component.html',
  providers: [TermConversionService]
})
export class DefaultTermBuilderComponent {

  options: ITreeOptions = {
    displayField: 'display',
    childrenField: 'operands'
  };

  nodes: TreeNode[];

  constructor(private termConversionService: TermConversionService) {

  }

  get term(): Term {
    return this.termConversionService.convertTreeToTerm(this.nodes);
  }

  @Input()
  set term(term: Term) {
    this.nodes = this.termConversionService.convertTermToTree(term);
  }
}
