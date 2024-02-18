import { storage } from '@/utils/storage';

const LOCAL_NAME = 'settingsStorage';

export interface SettingsState {
  systemMessage: string;
  temperature: number;
  top_p: number;
}

export function defaultSetting(): SettingsState {
  return {
    systemMessage:
      "You are ChatGPT, a large language model trained by OpenAI. Follow the user's instructions carefully. Respond using markdown.",
    temperature: 0.8,
    top_p: 1,
  };
}

export function getLocalState(): SettingsState {
  const localSetting: SettingsState | undefined = storage.get(LOCAL_NAME);
  return { ...defaultSetting(), ...localSetting };
}

export function setLocalState(setting: SettingsState): void {
  storage.set(LOCAL_NAME, setting);
}

export function removeLocalState() {
  storage.remove(LOCAL_NAME);
}
