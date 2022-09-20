import { ReactNode, useEffect, useMemo } from "react";
import { createPortal } from "react-dom";

interface IModalPortalProps {
  children: ReactNode;
}

export const ModalPortalWrap = ({ children }: IModalPortalProps) => {
  // div tag 생성 > styled 적용.
  const subDiv = useMemo(() => document.createElement("div"), []);
  useEffect(() => {
    subDiv.id = "modal-portal-wrap";
    document.body.appendChild(subDiv);
    return () => subDiv.remove();
  }, [subDiv]);

  return createPortal(<>{children}</>, subDiv);
};
