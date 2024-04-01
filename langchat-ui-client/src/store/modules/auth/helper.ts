import { storage } from '@/utils/storage';

const LOCAL_NAME = 'SECRET_TOKEN';

export function getToken() {
  return storage.get(LOCAL_NAME);
}

export function setToken(token: string) {
  return storage.set(LOCAL_NAME, token);
}

export function removeToken() {
  return storage.remove(LOCAL_NAME);
}
