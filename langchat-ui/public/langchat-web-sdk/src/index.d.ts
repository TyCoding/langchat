export type WidgetConfig = {
    element: Element;
    url: string;
    apiKey: string;
    threadId: string | null;
    user: Record<any, any>;
    greetingMessage: string | null;
    disableErrorAlert: boolean;
};
declare function init(): Promise<void>;
declare function open(e: Event): void;
declare function close(): void;
declare const langchatChatWidget: {
    open: typeof open;
    close: typeof close;
    config: WidgetConfig;
    init: typeof init;
};
declare global {
    interface Window {
        langchatChatWidget: typeof langchatChatWidget;
    }
}
export default langchatChatWidget;
