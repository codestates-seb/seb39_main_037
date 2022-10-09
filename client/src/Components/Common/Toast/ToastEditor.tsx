// Toast 에디터
import "@toast-ui/editor/dist/toastui-editor.css";
import "tui-color-picker/dist/tui-color-picker.css";
import "@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css";

import colorSyntax from "@toast-ui/editor-plugin-color-syntax";
import { Editor } from "@toast-ui/react-editor";
import { postImg } from "Hooks/Api/Register/postImg";
import React, { useRef } from "react";

interface IToastEditor {
  thumbnail?: string | null;
  setThumbnail?: React.Dispatch<React.SetStateAction<string | null>>;
  setContent: React.Dispatch<React.SetStateAction<string>>;
  height?: string;
}

const ToastEditor = ({
  thumbnail,
  setThumbnail,
  setContent,
  height = "500px",
}: IToastEditor) => {
  const editorRef = useRef<Editor>(null); // 입력받은 텍스트 하기

  const handleChange = () => {
    if (setContent) {
      setContent(editorRef?.current?.getInstance()?.getMarkdown() || "");
    }
  };

  return (
    <div style={{ margin: "10px" }}>
      <Editor
        placeholder="내용을 입력해주세요."
        previewStyle="vertical" // 미리보기 스타일 지정
        height={height} // 에디터 창 높이
        initialEditType="wysiwyg" // 초기 입력모드 설정(디폴트 markdown)
        toolbarItems={[
          // 툴바 옵션 설정
          ["heading", "bold", "italic", "strike"],
          ["hr", "quote"],
          ["ul", "ol", "task", "indent", "outdent"],
          ["table", "image", "link"],
          ["code", "codeblock"],
        ]}
        plugins={[colorSyntax]}
        useCommandShortcut={false}
        ref={editorRef}
        onChange={handleChange}
        hooks={{
          addImageBlobHook: async (blob, callback) => {
            const imgUrl: string = await postImg({ imgFile: blob });
            if (!thumbnail) {
              setThumbnail?.(imgUrl);
            }
            callback(imgUrl);
          },
        }}
      />
    </div>
  );
};

export default ToastEditor;
