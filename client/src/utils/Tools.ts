export const validateEmail = (contactEmail: string) => {
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return emailPattern.test(contactEmail) ? null : 'email_invalid'
}

export const getEventName = (eventName: string) => {
  return eventName ? eventName.replace(/\s+/g, '-').toLowerCase() : '';
}

export const getRoute = (route: string) => {
  return !/^\/event\/.*/.test(route);
}
