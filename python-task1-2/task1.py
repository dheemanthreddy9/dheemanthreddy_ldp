def find_single_number(nums):
    for num in nums:
        if nums.count(num) == 1:
            return num

nums = [4, 1, 2, 1, 2]
print(find_single_number(nums))
