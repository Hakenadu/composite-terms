import {TreeNode} from './tree-node';
import {Term} from './term';
import {Inject} from '@angular/core';
import {CONSTANT_VALUE_TO_STRING_CONVERTER_SERVICE} from '../../terms-tokens';
import {ConstantValueToStringConverterService} from './convert/constant-value-to-string-converter.service';

/**
 * controls the conversion between instances of Term and TreeNode
 */
export class TermConversionService {

  constructor(@Inject(CONSTANT_VALUE_TO_STRING_CONVERTER_SERVICE) private toStringConverterService: ConstantValueToStringConverterService) {

  }

  convertTermToTree(term: Term): TreeNode[] {
    if (term === undefined || term === null) {
      throw  new Error('no term passed');
    }

    return [this.convertTermToNode(term, 0)];
  }

  private convertTermToNode(term: Term, nodeId: number): TreeNode {
    if (term.type === 'constant') {
      return {
        type: term.type,
        id: nodeId,
        value: term.value,
        datatype: term.datatype,
        display: `Constant: ${this.toStringConverterService.convertToString(term.datatype, term.value)}`
      };
    } else if (term.type === 'variable') {
      return {
        type: term.type,
        id: nodeId,
        name: term.name,
        display: `Variable: ${term.name}`,
      };
    } else if (term.type === 'operation') {
      const resultOperands: TreeNode[] = [];

      let childId = nodeId + 1;
      for (const operand of term.operands) {
        resultOperands.push(this.convertTermToNode(operand, childId++));
      }

      return {
        type: term.type,
        id: nodeId,
        operator: term.operator,
        operands: resultOperands,
        display: `Operation: ${term.operator}`
      };
    }
  }

  convertTreeToTerm(nodes: TreeNode[]): Term {
    if (nodes === undefined || nodes === null) {
      throw new Error('no nodes passed');
    }

    if (nodes.length !== 1) {
      throw new Error('incorrect number of nodes passed');
    }

    return this.convertNodeToTerm(nodes[0]);
  }

  private convertNodeToTerm(node: TreeNode): Term {
    if (node.type === 'constant') {
      return {
        type: node.type,
        datatype: node.datatype,
        value: node.value
      };
    } else if (node.type === 'variable') {
      return {
        type: node.type,
        name: node.name
      };
    } else if (node.type === 'operation') {
      const resultOperands: Term[] = [];

      for (const operand of node.operands) {
        resultOperands.push(this.convertNodeToTerm(operand));
      }

      return {
        type: node.type,
        operator: node.operator,
        operands: resultOperands
      };
    }

    throw new Error('unsupported type: ' + node.type);
  }
}

