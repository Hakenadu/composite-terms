import {InjectionToken} from '@angular/core';
import {ConstantValueToStringConverterService} from './builder/shared/convert/constant-value-to-string-converter.service';

export const CONSTANT_VALUE_TO_STRING_CONVERTER_SERVICE = new InjectionToken<ConstantValueToStringConverterService>(
  'ConstantValueToStringConverterService', {
    providedIn: 'root',
    factory: () => new ConstantValueToStringConverterService()
  });
