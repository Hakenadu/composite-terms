export interface Constant {
  type: 'constant';
  datatype: string;
  value: any;
}

export interface Variable {
  type: 'variable';
  name: string;
}

export interface Operation {
  type: 'operation';
  operator: string;
  operands: Term[];
}

export type Term = Constant | Variable | Operation;
