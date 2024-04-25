import { breakpointsTailwind, useBreakpoints } from '@vueuse/core';

export const useBasicLayout = () => {
  const breakpoints = useBreakpoints(breakpointsTailwind);
  const isMobile = breakpoints.smaller('sm');
  return { isMobile };
};
