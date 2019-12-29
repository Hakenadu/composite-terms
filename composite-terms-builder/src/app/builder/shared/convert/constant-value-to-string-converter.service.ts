import {StringToStringConverter} from './string-to-string-converter';
import {NumberToStringConverter} from './number-to-string-converter';
import {DateToStringConverter} from './date-to-string-converter';

export interface ConstantValueToStringConverter<T> {

  convertValueToString(value: T): string;
}

export class ConstantValueToStringConverterService {

  protected valueToStringConverters: Map<string, ConstantValueToStringConverter<any>>;

  constructor() {
    this.valueToStringConverters = new Map();

    this.valueToStringConverters.set('string', new StringToStringConverter());
    this.valueToStringConverters.set('number', new NumberToStringConverter());
    this.valueToStringConverters.set('date', new DateToStringConverter());
  }

  convertToString(datatype: string, value: any): string {
    if (!this.valueToStringConverters.has(datatype)) {
      throw new Error('no converter registered for datatype ' + datatype);
    }
    return this.valueToStringConverters.get(datatype).convertValueToString(value);
  }
}
