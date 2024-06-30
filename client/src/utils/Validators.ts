export const validateEmail = (contactEmail: string) => {
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return emailPattern.test(contactEmail) ? null : 'email_invalid'
}

export const validateEventName = (eventName: string) => {
  return eventName ? eventName.replace(/\s+/g, '-').toLowerCase() : '';
}

export const validateRoute = (route: string) => {
  return !/^\/event\/.*/.test(route);
}
