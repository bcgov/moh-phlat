class ResizeObserverStub {
  observe() {}
  unobserve() {}
  disconnect() {}
}

window.ResizeObserver = window.ResizeObserver || ResizeObserverStub;

const noop = () => {};
Object.defineProperty(window, 'scrollTo', { value: noop, writable: true });