import "@toast-ui/editor/dist/toastui-editor-viewer.css";

import { Viewer } from "@toast-ui/react-editor";

interface IToastViewer {
  contents: string;
}
const ToastViewer = ({ contents }: IToastViewer) => {
  return <Viewer initialValue={contents || ""} />;
};

export default ToastViewer;
