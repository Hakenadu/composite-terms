import {ConstantValueToStringConverter} from './constant-value-to-string-converter.service';

export class StringToStringConverter implements ConstantValueToStringConverter<string> {

  convertValueToString(value: string): string {
    return value;
  }
}
