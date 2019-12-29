export interface TreeNode {

  id: number;
  type: 'constant' | 'variable' | 'operation';
  datatype?: string;
  name?: string;
  value?: any;
  operator?: string;
  operands?: TreeNode[];

  // computed
  display: string;
}
