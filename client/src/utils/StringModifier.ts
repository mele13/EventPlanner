export function upperFirst(str: string): string {
  return str.charAt(0).toUpperCase() + str.slice(1);
}

export function getNumberFromString(str: string | string[]): number | null {
  const targetString = Array.isArray(str) ? str[str.length - 1] : str;
  const match = targetString.match(/\d+/);
  return match ? parseInt(match[0]) : null;
}
