/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

export function isNumber<T extends number>(value: T | unknown): value is number {
  return Object.prototype.toString.call(value) === '[object Number]';
}

export function isString<T extends string>(value: T | unknown): value is string {
  return Object.prototype.toString.call(value) === '[object String]';
}

export function isBoolean<T extends boolean>(value: T | unknown): value is boolean {
  return Object.prototype.toString.call(value) === '[object Boolean]';
}

export function isNull<T extends null>(value: T | unknown): value is null {
  return Object.prototype.toString.call(value) === '[object Null]';
}

export function isUndefined<T extends undefined>(value: T | unknown): value is undefined {
  return Object.prototype.toString.call(value) === '[object Undefined]';
}

export function isObject<T extends object>(value: T | unknown): value is object {
  return Object.prototype.toString.call(value) === '[object Object]';
}

export function isArray<T extends any[]>(value: T | unknown): value is T {
  return Object.prototype.toString.call(value) === '[object Array]';
}

export function isFunction<T extends (...args: any[]) => any | void | never>(
  value: T | unknown
): value is T {
  return Object.prototype.toString.call(value) === '[object Function]';
}

export function isDate<T extends Date>(value: T | unknown): value is T {
  return Object.prototype.toString.call(value) === '[object Date]';
}

export function isRegExp<T extends RegExp>(value: T | unknown): value is T {
  return Object.prototype.toString.call(value) === '[object RegExp]';
}

export function isPromise<T extends Promise<any>>(value: T | unknown): value is T {
  return Object.prototype.toString.call(value) === '[object Promise]';
}

export function isSet<T extends Set<any>>(value: T | unknown): value is T {
  return Object.prototype.toString.call(value) === '[object Set]';
}

export function isMap<T extends Map<any, any>>(value: T | unknown): value is T {
  return Object.prototype.toString.call(value) === '[object Map]';
}

export function isFile<T extends File>(value: T | unknown): value is T {
  return Object.prototype.toString.call(value) === '[object File]';
}

export function isBlank(val: unknown): val is null {
  return val === null || val === undefined || val === '' || String(val).trim() === '';
}
export const isDef = <T = unknown>(val?: T): val is T => {
  return typeof val !== 'undefined';
};

export const isUnDef = <T = unknown>(val?: T): val is T => {
  return !isDef(val);
};
export function isNullOrUnDef(val: unknown): val is null | undefined {
  return isUnDef(val) || isNull(val);
}

export function isWhitespace(val: unknown) {
  return val === '';
}

export function isNullOrWhitespace(val: unknown) {
  return isNullOrUnDef(val) || isWhitespace(val);
}
