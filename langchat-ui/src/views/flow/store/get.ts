import { Component, markRaw } from 'vue';
import {
  SparklesOutline,
  DocumentTextOutline,
  DownloadOutline,
  CodeSlashOutline,
} from '@vicons/ionicons5';
import { LLMPin, EndPin, StartPin, KnowledgeWeb, KnowledgeDoc, CodePin } from '@/views/flow/pin';
import { CodeNode, End, HttpNode, LLMNode, Start } from '@/views/flow/node';
import { renderPropsIcon } from '@/utils';
import type { FlowExportObject } from '@vue-flow/core/dist/types/flow';
import HttpPin from '@/views/flow/pin/node/HttpPin.vue';

export enum TypeEnum {
  Start = 'Start',
  End = 'End',
  LLM = 'LLM',
  Http = 'Http',
  Code = 'Code',
}

export enum PluginEnum {
  Input = 'Input',
  ExecuteCode = 'ExecuteCode',
  Knowledge_Web = 'From Web',
  Knowledge_Doc = 'From Doc',
}

enum ColEnum {
  Base = 'Base',
  Node = 'Node',
  Ai = 'Ai',
  SendMessage = 'Send Message',
  Knowledge = 'Knowledge List',
}

// 节点类型定义，注意非节点不要定义此变量
export const nodeTypes = {
  [TypeEnum.Start]: markRaw(Start),
  [TypeEnum.End]: markRaw(End),
  [TypeEnum.LLM]: markRaw(LLMNode),
  [TypeEnum.Http]: markRaw(HttpNode),
  [TypeEnum.Code]: markRaw(CodeNode),
};

export interface Pin {
  type?: TypeEnum | PluginEnum;
  label?: string;
  des?: string;
  component?: Component;
  col?: ColEnum;
  isNode?: boolean;
  data?: any;
  icon?: any;
}

const nodePins: Pin[] = [
  {
    type: TypeEnum.LLM,
    component: LLMPin,
    col: ColEnum.Node,
    isNode: true,
    des: '利用LLM进行文本消息问答',
    icon: renderPropsIcon(SparklesOutline, { color: '#8a2be2', size: '16px' }),
    data: {
      model: 'gpt-4',
      temperate: 0.7,
    },
  },
  {
    type: TypeEnum.Http,
    component: HttpPin,
    col: ColEnum.Node,
    isNode: true,
    des: '发送HTTP请求',
    icon: renderPropsIcon(DownloadOutline, { color: '#8a2be2', size: '16px' }),
  },
  {
    type: TypeEnum.Code,
    component: CodePin,
    col: ColEnum.Node,
    isNode: true,
    des: 'Code...',
    icon: renderPropsIcon(CodeSlashOutline, { color: '#8a2be2', size: '16px' }),
  },

  { type: TypeEnum.End, component: EndPin, col: ColEnum.Base, isNode: true },
  { type: TypeEnum.Start, component: StartPin, col: ColEnum.Base, isNode: true },
];

const pluginPins: Pin[] = [
  {
    type: PluginEnum.Input,
    label: 'Input Node',
    component: StartPin,
    col: ColEnum.SendMessage,
    isNode: false,
    icon: renderPropsIcon(SparklesOutline, { color: '#8a2be2', size: '16px' }),
  },

  {
    type: PluginEnum.ExecuteCode,
    label: 'ExecuteCode Node',
    component: StartPin,
    col: ColEnum.Ai,
    isNode: false,
    icon: renderPropsIcon(SparklesOutline, { color: '#8a2be2', size: '16px' }),
  },
  {
    type: PluginEnum.Knowledge_Web,
    label: 'Knowledge From Web',
    component: KnowledgeWeb,
    col: ColEnum.Knowledge,
    isNode: false,
    icon: renderPropsIcon(DocumentTextOutline, { color: '#8a2be2', size: '16px' }),
  },
  {
    type: PluginEnum.Knowledge_Doc,
    label: 'Knowledge From Doc',
    component: KnowledgeDoc,
    col: ColEnum.Knowledge,
    isNode: false,
    icon: renderPropsIcon(DocumentTextOutline, { color: '#8a2be2', size: '16px' }),
  },
];

export function getPin(type: string | undefined, isNode: boolean): Pin | undefined {
  if (type === undefined) {
    return;
  }
  const pins = isNode ? nodePins : pluginPins;
  const list = pins.filter((i) => i.type?.toLowerCase() === type.toLowerCase());
  return list.length > 0 ? list[0] : undefined;
}

export const collapses = (
  isNode: boolean
): {
  key: string;
  value: Pin[];
}[] => {
  const pins = isNode ? nodePins : pluginPins;
  const transformedArray = pins.reduce((result, pin) => {
    const { col } = pin;
    if (col == undefined || col == ColEnum.Base) {
      return result;
    }
    if (col in result) {
      result[col].push(pin);
    } else {
      result[col] = [pin];
    }
    return result;
  }, {});
  // @ts-ignore
  return Object.entries(transformedArray).map(([key, value]) => ({
    key,
    value,
  }));
};

export function getDatas(obj: FlowExportObject): any[] {
  const data: any[] = [];
  obj.edges.forEach((i) => {
    data.push({
      id: i.id,
      source: i.source,
      target: i.target,
      type: i.type,
      animated: i.animated,
      data: i.data,
      label: i.label,
    });
  });
  obj.nodes.forEach((i) => {
    data.push({
      id: i.id,
      type: i.type,
      position: i.position,
      data: i.data,
      label: i.label,
    });
  });
  return data;
}

export function renderNodeIcon(type: string) {
  const list = nodePins.filter((i) => i.type == type);
  const pluginPin = pluginPins.filter((i) => i.type == type);
  return list.length > 0 ? list[0].icon : pluginPin.length > 0 ? pluginPin[0].icon : undefined;
}

export function renderNodeDes(type: string) {
  const list = nodePins.filter((i) => i.type == type);
  return list.length > 0 ? list[0].des : undefined;
}
