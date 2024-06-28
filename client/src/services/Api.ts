import axios, { type AxiosRequestConfig } from 'axios'

const BASE_URL = import.meta.env.VITE_LOC_URL
const TOKEN_VAL = import.meta.env.VITE_AUTH_TOKEN
const TOKEN_HEADER =import.meta.env.VITE_AUTH_TOKEN_HEADER

async function sendRequest(method: string, url: string, data?: any) {
  const config: AxiosRequestConfig = {
    method,
    url: BASE_URL + url,
    headers: {
      [TOKEN_HEADER]: TOKEN_VAL,
    },
    data,
  };
  
  try {
    const response = await axios.request(config)
    return response.data
  } catch (error) {
    console.error(error)
    throw error
  }
}

export { sendRequest }