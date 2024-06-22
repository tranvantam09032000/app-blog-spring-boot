
export function getURLSearchParams(filter: Record<string, any>) {
  const params = new URLSearchParams();

  Object.entries(filter).forEach(([key, value]) => {
    if (!value || (Array.isArray(value) && value.length === 0)) return;

    params.set(key, value);
  });

  return params;
}
